package simple.demo.web_log

sealed class MainAction {

    object OpenUI : MainAction()
    object OpenComposeUI : MainAction()

}