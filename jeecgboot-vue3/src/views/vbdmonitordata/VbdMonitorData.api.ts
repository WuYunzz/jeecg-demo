import { defHttp } from '/@/utils/http/axios';

// 集中管理 API 路径
enum Api {
  list = '/bmjc/vbdMonitorData/list',
  save = '/bmjc/vbdMonitorData/add',
  edit = '/bmjc/vbdMonitorData/edit',
  deleteOne = '/bmjc/vbdMonitorData/delete',
  deleteBatch = '/bmjc/vbdMonitorData/deleteBatch',
  importExcel = '/bmjc/vbdMonitorData/importExcel',
  exportXls = '/bmjc/vbdMonitorData/exportXls',
  queryDetailByMainId = '/bmjc/vbdMonitorData/queryDetailByMainId',
}

/**
 * 导出 api URL (给 useListPage 使用)
 */
export const getExportUrl = Api.exportXls;

/**
 * 导入 api URL (给 useListPage 使用)
 */
export const getImportUrl = Api.importExcel;

/**
 * 列表查询
 * @param params
 */
export const list = (params) => defHttp.get({ url: Api.list, params });

/**
 * 删除单个
 */
export const deleteOne = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.deleteOne, params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};

/**
 * 批量删除
 * @param params
 */
export const batchDelete = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.deleteBatch, data: params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
};

/**
 * 保存或者更新
 * @param params
 * @param isUpdate
 */
export const saveOrUpdate = (params, isUpdate) => {
  const url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({ url: url, params });
};

/**
 * 通过主表ID查询子表明细
 * @param id
 */
export const queryDetailByMainId = (id) => {
  return defHttp.get({ url: Api.queryDetailByMainId, params: { id } });
};
