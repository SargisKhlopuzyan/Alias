package app.sargis.khlopuzyan.alias.ui.common

/**
 * Created by Sargis Khlopuzyan, on 2/28/2020.
 *
 * @author Sargis Khlopuzyan (sargis.khlopuzyan@fcc.am)
 */
interface BindableAdapter<T> {
    fun setItems(items: T?)
}