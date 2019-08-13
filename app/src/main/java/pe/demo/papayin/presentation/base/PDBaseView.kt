package pe.demo.papayin.presentation.base

interface PDBaseView<T> {
    val presenter: T
    fun showProgressLoading()
    fun hideProgressLoading()
}
