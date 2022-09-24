package test.interview.listApp.ui.entrymodel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import test.interview.listApp.databinding.ItemEntryModelBinding
import test.interview.listApp.data.model.EntryModel

class EntryModelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val entryModelList = ArrayList<EntryModel>()
    private lateinit var context: Context

    inner class EntryModelViewHolder(val binding: ItemEntryModelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                Toast.makeText(context, entryModelList[absoluteAdapterPosition].toString(), Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(entryModel: EntryModel) {
            binding.entryModel = entryModel
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EntryModelViewHolder(
            ItemEntryModelBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EntryModelViewHolder).bind(entryModelList[holder.absoluteAdapterPosition])
    }

    override fun getItemCount() = entryModelList.size

    fun insertItemsToList(list: ArrayList<EntryModel>) {
        entryModelList.clear()
        entryModelList.addAll(list)
        notifyItemRangeRemoved(0, itemCount)
        notifyItemRangeInserted(0, entryModelList.size)
    }

    fun createContext(context: Context) {
        this.context = context
    }

}