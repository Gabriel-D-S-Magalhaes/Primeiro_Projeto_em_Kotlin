package br.com.gabriel.primeiroprojetoemkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gabriel.primeiroprojetoemkotlin.R
import br.com.gabriel.primeiroprojetoemkotlin.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.programming_language_item.view.*

/**
 * Created by Gabriel Magalhaes on 29/07/2020.
 * @author Gabriel dos S. Magalhães
 */
class ProgrammingLanguageAdapter(
    private val items: List<ProgrammingLanguage>,
    private val listener: (ProgrammingLanguage) -> Unit
) : RecyclerView.Adapter<ProgrammingLanguageAdapter.ViewHolder>() {

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflar o layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.programming_language_item, parent, false)
        return ViewHolder(view)// chama o ViewHolder.
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = items.size // retornará a quantidade de itens na RecyclerView.


    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recebe uma posição
        val item = items[position]// encontra o item correto
        holder.bindView(item, listener)// realiza o bindView().
    }

    /**
     * Atua "segurando" as informações da view, economizando recursos conforme o usuário visualiza
     * diversos elementos.
     * */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * Recebe as informações de cada item e aplica nas Views e por fim aplicar o listener também
         * recebido.
         * */
        fun bindView(item: ProgrammingLanguage, listener: (ProgrammingLanguage) -> Unit) =
            with(itemView) {
                // atribuir os valores às Views correspondentes,
                ivMain.setImageResource(item.imageResourceId)
                tvTitle.text = item.title
                tvLaunchYear.text = item.year.toString()
                tvDescription.text = item.description

                setOnClickListener { listener(item) }
            }
    }
}