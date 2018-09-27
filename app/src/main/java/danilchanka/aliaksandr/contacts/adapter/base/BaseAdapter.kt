package danilchanka.aliaksandr.contacts.adapter.base

import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAdapter<E, VM : ViewModel>(private val viewModel: VM) : RecyclerView.Adapter<BaseAdapter.BindingHolder>() {

    private var mElements: List<E>? = null

    abstract val itemLayoutId: Int
    abstract val variableId: Int
    abstract val viewModelId: Int

    init {
        mElements = ArrayList()
    }

    fun setElements(elements: List<E>) {
        mElements = elements
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val v = LayoutInflater.from(parent.context).inflate(itemLayoutId,
                parent, false)
        return BindingHolder(v)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val element = mElements!![position]
        holder.binding!!.setVariable(variableId, element)
        holder.binding.setVariable(viewModelId, viewModel)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return mElements!!.size
    }

    class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding: ViewDataBinding? = DataBindingUtil.bind(itemView)

    }
}