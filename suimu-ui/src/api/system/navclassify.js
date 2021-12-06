import request from '@/utils/request'

// 查询分类列表
export function listNavclassify(query) {
    return request({
        url: '/system/navclassify/list',
        method: 'get',
        params: query
    })
}

// 查询分类列表
export function treeNavclassify(query) {
    return request({
        url: '/system/navclassify/tree',
        method: 'get',
        params: query
    })
}

// 查询分类详细
export function getNavclassify(id) {
    return request({
        url: '/system/navclassify/' + id,
        method: 'get'
    })
}

// 新增分类
export function addNavclassify(data) {
    return request({
        url: '/system/navclassify',
        method: 'post',
        data: data
    })
}

// 修改分类
export function updateNavclassify(data) {
    return request({
        url: '/system/navclassify',
        method: 'put',
        data: data
    })
}

// 删除分类
export function delNavclassify(id) {
    return request({
        url: '/system/navclassify/' + id,
        method: 'delete'
    })
}
