import request from '@/utils/request'

// 查询分类列表
export function classifyTree(query) {
    return request({
        url: '/system/navigation/tab/tree',
        method: 'get',
        params: query
    })
}