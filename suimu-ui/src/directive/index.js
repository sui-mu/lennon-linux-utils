import hasRole from './permission/hasRole'
import hasPermi from './permission/hasPermi'
import dialogDrag from './dialog/drag'
import dialogDragWidth from './dialog/dragWidth'
import dialogDragHeight from './dialog/dragHeight'
import title from './title/title'

const install = function (Vue) {
    Vue.directive('hasRole', hasRole)
    Vue.directive('hasPermi', hasPermi)
    Vue.directive('dialogDrag', dialogDrag)
    Vue.directive('dialogDragWidth', dialogDragWidth)
    Vue.directive('dialogDragHeight', dialogDragHeight)
    Vue.directive('title', title)
}

if (window.Vue) {
    window['hasRole'] = hasRole
    window['hasPermi'] = hasPermi
    window['title'] = title
    Vue.use(install); // eslint-disable-line
}

export default install
