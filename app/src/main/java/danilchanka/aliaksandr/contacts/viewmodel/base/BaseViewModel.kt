package danilchanka.aliaksandr.contacts.viewmodel.base

import danilchanka.aliaksandr.contacts.view.base.BaseView

interface BaseViewModel<V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}