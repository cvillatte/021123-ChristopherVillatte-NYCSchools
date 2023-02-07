package com.example.firstnetworkapi.view.NYCschoolsadapter

import android.view.ViewGroup

class NYCschoolsadapter
    private val itemSet: MutableList<com.example.firtnetworkapi.view.NYCschoolapdapter> = mutableListOf(),
    private val onItemClick: (itemid: String) -> Unit
) : RecyclerView.Adapter<NYCSchoolViewholder>() {

 override fun onCreateViewHolder (parent: ViewGroup,  viewType: Int): Nycviewholder(
     NYCschoolsItemBinding.inflate(
         layoutInflater.from(parent.context),
         parent,
         attachtoparent: false

     )
 )

    override fun onBindViewHolder(holder: Nycviewholder, POSITION: Int) = holder.bind(itemSet[position], onItemClick)
    holder.bind(itemSet[position], onItemClick

     override fun getItemCount(): Int = ItemSet.size

}

class Nycviewholder(
    private val binding: NYCschoolsItemBinding
): Recycler.ViewHolder(binding.root) {

    fun bind (item: com.example.firstnetworkapi.model) (String) -> Unit)
        binding.itemName.text = item.name ?: "No name"

    itemView.setOnClickListener
        item.name?let(onItemClick)
}

    }

}

)