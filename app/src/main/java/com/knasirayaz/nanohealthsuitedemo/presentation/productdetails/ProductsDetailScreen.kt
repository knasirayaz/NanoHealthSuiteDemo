package com.knasirayaz.nanohealthsuitedemo.presentation.productdetails

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.knasirayaz.nanohealthsuitedemo.R
import com.knasirayaz.nanohealthsuitedemo.databinding.FragmentProductDetailBinding
import com.knasirayaz.nanohealthsuitedemo.domain.common.BindingAdapters.loadImage
import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.common.SnackBarTypes
import com.knasirayaz.nanohealthsuitedemo.domain.common.showToast
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponseItem
import com.knasirayaz.nanohealthsuitedemo.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsDetailScreen : BaseFragment<FragmentProductDetailBinding>(R.layout.fragment_product_detail){

    private val viewModel: ProductDetailsViewModel by viewModels()
    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var mBottomSheet : ConstraintLayout? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
        apiCall()
        setBottomSheet(view)
    }

    private fun clickListeners() {
        viewBinding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun apiCall() {
        viewModel.getProductDetailsObserver().observe(viewLifecycleOwner){ resultStates ->
            when(resultStates){
                is ResultStates.Failed -> {
                    showToast(requireView(), resultStates.error, SnackBarTypes.ERROR)
                }
                is ResultStates.ValidationFailed ->{
                    showToast(requireView(), getString(resultStates.error), SnackBarTypes.ERROR)
                }
                is ResultStates.Success ->{
                    setData(resultStates.data)
                }
                is ResultStates.Loading -> {
                    if(resultStates.isLoading){
                        viewBinding.progressBar.visibility = View.VISIBLE
                    }else{
                        viewBinding.progressBar.visibility = View.GONE
                    }
                }
                else -> {}
            }
        }.also {
            arguments?.let {
                viewModel.getProductDetails(it.getInt("id"))
            }
        }
    }

    private fun setData(data: ProductsResponseItem) {
        viewBinding.tvPrice.text = "${data.price} AED"
        viewBinding.tvProductDesc.text = data.description
        viewBinding.tvReviews.text = "Reviews (${data.rating.count})"
        viewBinding.ratingbar.rating = data.rating.rate.toFloat()
        viewBinding.tvRatingCount.text = data.rating.rate.toString()
        viewBinding.ivProductImage.loadImage(data.image)
    }

    private var isCollapsed = true

    private fun setBottomSheet(view: View) {
        mBottomSheet = view.findViewById(R.id.bottomSheet)
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet!!)
        mBottomSheetBehavior.isDraggable = false

        viewBinding.ivCollapse.setOnClickListener {
            if(isCollapsed) {
                isCollapsed = false
                mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                viewBinding.ivCollapse.loadImage(R.drawable.ic_arrow_down)
            }else {
                isCollapsed = true
                mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                viewBinding.ivCollapse.loadImage(R.drawable.ic_top_arrow)
            }

        }
    }
}