<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="title" :defaultFullscreen="true" @ok="handleSubmit">
    <BasicForm @register="registerForm">
      <template #paperSubTable>
        <div class="paper-section">
          <div class="section-header">数 据 记 录</div>

          <a-table :dataSource="subList" :pagination="false" bordered size="small" row-key="rowId">
            <a-table-column-group align="center">
              <template #title>时间</template>
              <a-table-column dataIndex="observeTime" align="center" width="15%">
                <template #default="{ record }">
                  <a-input v-model:value="record.observeTime" size="small" placeholder="1h/2h..." style="text-align: center" />
                </template>
              </a-table-column>
            </a-table-column-group>

            <a-table-column-group title="试 验 组" align="center">
              <a-table-column title="重复1(总/死)" align="center" width="26%">
                <template #default="{ record }">
                  <div class="cell-flex">
                    <a-input-number v-model:value="record.t1Total" :min="0" size="small" style="width: 100%; text-align: center" />
                    <span class="divider">/</span>
                    <a-input-number v-model:value="record.t1Dead" :min="0" size="small" style="width: 100%; text-align: center" />
                  </div>
                </template>
              </a-table-column>
              <a-table-column title="重复2(总/死)" align="center" width="26%">
                <template #default="{ record }">
                  <div class="cell-flex">
                    <a-input-number v-model:value="record.t2Total" :min="0" size="small" style="width: 100%; text-align: center" />
                    <span class="divider">/</span>
                    <a-input-number v-model:value="record.t2Dead" :min="0" size="small" style="width: 100%; text-align: center" />
                  </div>
                </template>
              </a-table-column>
            </a-table-column-group>

            <a-table-column-group title="空 白 对 照 组" align="center">
              <a-table-column title="重复1(总/死)" align="center" width="26%">
                <template #default="{ record }">
                  <div class="cell-flex">
                    <a-input-number v-model:value="record.c1Total" :min="0" size="small" style="width: 100%; text-align: center" />
                    <span class="divider">/</span>
                    <a-input-number v-model:value="record.c1Dead" :min="0" size="small" style="width: 100%; text-align: center" />
                  </div>
                </template>
              </a-table-column>

              <a-table-column title="操作" align="center" width="7%">
                <template #default="{ index }">
                  <a-button type="link" danger @click="removeRow(index)"><Icon icon="ant-design:delete-outlined" /></a-button>
                </template>
              </a-table-column>
            </a-table-column-group>
          </a-table>

          <a-button type="dashed" block class="mt-2" @click="addRow">+ 添加观测时间点</a-button>
        </div>
      </template>
    </BasicForm>

    <div class="formula-box">
      <p>注：P=(K/N)*100%。P—死亡率/捕杀率；K—死亡数；N—动物总数。</p>
    </div>
  </BasicModal>
</template>

<script lang="ts" setup>
  import { ref, computed, unref } from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, useForm } from '/@/components/Form/index';
  import { formSchema } from '../VbdMonitorData.data';
  import { saveOrUpdate, queryDetailByMainId } from '../VbdMonitorData.api';

  const emit = defineEmits(['register', 'success']);
  const isUpdate = ref(false);
  const subList = ref<any[]>([]); // 存放中间表格的数据

  // 1. 初始化主表单
  const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
    schemas: formSchema,
    showActionButtonGroup: false,
    baseColProps: { span: 12 },
    labelWidth: 120,
  });

  // 2. 弹窗打开回调（核心逻辑：回显）
  const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
    await resetFields();
    subList.value = [];
    isUpdate.value = !!data?.isUpdate;

    if (unref(isUpdate)) {
      // 回显主表
      await setFieldsValue({ ...data.record });
      // 回显子表（这里需要把扁平化的子表记录转换成表格的行，或者直接按行存取）
      const res = await queryDetailByMainId(data.record.id);
      if (res) subList.value = transformDataToRows(res); // 转换逻辑根据你后端存取方式定
    }
  });

  const title = computed(() => (unref(isUpdate) ? '编辑药效试验' : '新增药效试验'));

  // 3. 表格行操作
  const addRow = () => subList.value.push({ rowId: Date.now() });
  const removeRow = (index) => subList.value.splice(index, 1);

  // 4. 提交逻辑
  async function handleSubmit() {
    try {
      const mainValues = await validate(); // 校验主表（上、下部分）
      setModalProps({ confirmLoading: true });

      // 组装数据：将表格行数据平铺回子表列表格式发给后端
      const postData = {
        ...mainValues,
        vbdMonitorDetailList: transformRowsToData(subList.value),
      };

      await saveOrUpdate(postData, isUpdate.value);
      closeModal();
      emit('success');
    } finally {
      setModalProps({ confirmLoading: false });
    }
  }

  // 数据转换辅助函数（示例）
  function transformRowsToData(rows) {
    // 根据你的业务，将一行中的 T1, T2 等数据转为多条数据库记录
    return rows.flatMap((row) => [
      { observeTime: row.observeTime, groupType: '1', repeatNum: 1, animalTotal: row.t1Total, deadNum: row.t1Dead },
      { observeTime: row.observeTime, groupType: '2', repeatNum: 1, animalTotal: row.c1Total, deadNum: row.c1Dead },
    ]);
  }
</script>

<style lang="less" scoped>
  .paper-section {
    width: 100%;
    box-sizing: border-box;
    border: 1px solid #d9d9d9;
    padding: 10px;
    margin: 20px 0;

    .section-header {
      text-align: center;
      font-size: 16px;
      font-weight: bold;
      background: #f5f5f5;
      margin-bottom: 10px;
      padding: 8px;
      letter-spacing: 2px;
    }
  }

  /* 让输入框平分空间，并在中间加个斜杠作为视觉分隔 */
  .cell-flex {
    display: flex;
    gap: 4px;
    align-items: center;
    justify-content: center;
    .divider {
      color: #999;
      font-weight: bold;
    }
  }

  /* 强行让数字输入框的文字居中 */
  :deep(.ant-input-number-input) {
    text-align: center;
  }

  .formula-box {
    margin-top: 15px;
    padding: 10px;
    background: #fffbe6;
    border: 1px solid #ffe58f;
    font-size: 12px;
  }
</style>
