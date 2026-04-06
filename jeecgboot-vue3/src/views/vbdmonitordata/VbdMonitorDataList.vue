<template>
  <div>
    <BasicTable @register="registerTable" :rowSelection="rowSelection">
      <template #tableTitle>
        <a-button type="primary" @click="handleAdd" preIcon="ant-design:plus-outlined">新增试验</a-button>
        <a-button type="primary" preIcon="ant-design:export-outlined" @click="onExportXls">导出</a-button>
        <j-upload-button type="primary" preIcon="ant-design:import-outlined" @click="onImportXls">导入</j-upload-button>
        <a-dropdown v-if="selectedRowKeys.length > 0">
          <template #overlay>
            <a-menu>
              <a-menu-item key="1" @click="batchHandleDelete"> <Icon icon="ant-design:delete-outlined" /> 删除 </a-menu-item>
            </a-menu>
          </template>
          <a-button>批量操作 <Icon icon="mdi:chevron-down" /></a-button>
        </a-dropdown>
      </template>

      <template #action="{ record }">
        <TableAction :actions="getTableAction(record)" :dropDownActions="getDropDownAction(record)" />
      </template>
    </BasicTable>

    <VbdMonitorDataModal @register="registerModal" @success="reload" />
  </div>
</template>

<script lang="ts" setup>
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { useListPage } from '/@/hooks/system/useListPage';
  import { useModal } from '/@/components/Modal';
  import VbdMonitorDataModal from './components/VbdMonitorDataModal.vue';
  import { columns, searchFormSchema } from './VbdMonitorData.data';
  import { list, deleteOne, batchDelete, getImportUrl, getExportUrl } from './VbdMonitorData.api';

  // 1. 注册列表 Hook
  const { tableContext, onExportXls, onImportXls } = useListPage({
    tableProps: {
      title: '药效试验记录',
      api: list,
      columns,
      formConfig: { schemas: searchFormSchema }, // 顶部的搜索表单
      actionColumn: { width: 120, fixed: 'right' },
    },
    exportConfig: { name: '药效试验原始记录', url: getExportUrl },
    importConfig: { url: getImportUrl, success: () => reload() },
  });

  const [registerTable, { reload }, { rowSelection, selectedRowKeys }] = tableContext;

  // 2. 注册弹窗 Hook
  const [registerModal, { openModal }] = useModal();

  const handleAdd = () => openModal(true, { isUpdate: false });
  const handleEdit = (record: Recordable) => openModal(true, { record, isUpdate: true });
  const handleDetail = (record: Recordable) => openModal(true, { record, isUpdate: true, showFooter: false });

  async function batchHandleDelete() {
    await batchDelete({ ids: selectedRowKeys.value }, () => {
      selectedRowKeys.value = [];
      reload();
    });
  }

  function getTableAction(record) {
    return [{ label: '编辑', onClick: handleEdit.bind(null, record) }];
  }

  function getDropDownAction(record) {
    return [
      { label: '详情', onClick: handleDetail.bind(null, record) },
      {
        label: '删除',
        popConfirm: {
          title: '是否确认删除',
          confirm: () => deleteOne({ id: record.id }, reload),
        },
      },
    ];
  }
</script>
