
<template>
  <BasicModal v-bind="$attrs" @register="registerModal" destroyOnClose :title="title" :maxHeight="500" :width="896" @ok="handleSubmit">
    <BasicForm @register="registerForm" ref="formRef" name="TestOrderMainForm"/>
    <!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey" animated @change="handleChangeTabs" class="jeecg-tab">
      <a-tab-pane tab="订单产品明细" key="testOrderProduct" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="testOrderProduct"
          :loading="testOrderProductTable.loading"
          :columns="testOrderProductTable.columns"
          :dataSource="testOrderProductTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
          />
      </a-tab-pane>
      <a-tab-pane tab="订单客户" key="testOrderCustomer" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="testOrderCustomer"
          :loading="testOrderCustomerTable.loading"
          :columns="testOrderCustomerTable.columns"
          :dataSource="testOrderCustomerTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
          />
      </a-tab-pane>
    </a-tabs>
  </BasicModal>
</template>

<script lang="ts" setup>
    import {ref, computed, unref,reactive} from 'vue';
    import {BasicModal, useModalInner} from '/@/components/Modal';
    import {BasicForm, useForm} from '/@/components/Form/index';
    import { JVxeTable } from '/@/components/jeecg/JVxeTable'
    import { useJvxeMethod } from '/@/hooks/system/useJvxeMethods.ts'
    import {formSchema,testOrderProductColumns,testOrderCustomerColumns} from '../TestOrderMain.data';
    import {saveOrUpdate,testOrderProductList,testOrderCustomerList} from '../TestOrderMain.api';
    import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils'
    import { useMessage } from '/@/hooks/web/useMessage';
    import { getDateByPicker } from '/@/utils';
    //日期个性化选择
    const fieldPickers = reactive({
    });
      const testOrderProductFieldPickers = reactive({
      });
      const testOrderCustomerFieldPickers = reactive({
      });
    const { createMessage } = useMessage();
    // Emits声明
    const emit = defineEmits(['register','success']);
    const isUpdate = ref(true);
    const formDisabled = ref(false);
    const refKeys = ref(['testOrderProduct', 'testOrderCustomer', ]);
    const activeKey = ref('testOrderProduct');
    const testOrderProduct = ref();
    const testOrderCustomer = ref();
    const tableRefs = {testOrderProduct, testOrderCustomer, };
    const testOrderProductTable = reactive({
          loading: false,
          dataSource: [],
          columns:testOrderProductColumns,
          baseRowStyle: { padding: "0 20px"}
    })
    const testOrderCustomerTable = reactive({
          loading: false,
          dataSource: [],
          columns:testOrderCustomerColumns,
          baseRowStyle: { padding: "0 20px"}
    })
    //表单配置
    const [registerForm, {setProps,resetFields, setFieldsValue, validate}] = useForm({
        schemas: formSchema,
        showActionButtonGroup: false,
        baseColProps: {span: 12},
        baseRowStyle: { padding: "0 20px" }
    });
     //表单赋值
    const [registerModal, {setModalProps, closeModal}] = useModalInner(async (data) => {
        //重置表单
        await reset();
        setModalProps({confirmLoading: false,showCancelBtn:data?.showFooter,showOkBtn:data?.showFooter});
        isUpdate.value = !!data?.isUpdate;
        formDisabled.value = !data?.showFooter;
        if (unref(isUpdate)) {
            //表单赋值
            await setFieldsValue({
                ...data.record,
            });
             requestSubTableData(testOrderProductList, {id:data?.record?.id}, testOrderProductTable)
             requestSubTableData(testOrderCustomerList, {id:data?.record?.id}, testOrderCustomerTable)
        }
        // 隐藏底部时禁用整个表单
       setProps({ disabled: !data?.showFooter })
    });
    //方法配置
    const [handleChangeTabs,handleSubmit,requestSubTableData,formRef] = useJvxeMethod(requestAddOrEdit,classifyIntoFormData,tableRefs,activeKey,refKeys);

    //设置标题
    const title = computed(() => (!unref(isUpdate) ? '新增' : !unref(formDisabled) ? '编辑' : '详情'));

    async function reset(){
      await resetFields();
      activeKey.value = 'testOrderProduct';
      testOrderProductTable.dataSource = [];
      testOrderCustomerTable.dataSource = [];
    }
    function classifyIntoFormData(allValues) {
         let main = Object.assign({}, allValues.formValue)
         return {
           ...main, // 展开
           testOrderProductList: allValues.tablesValue[0].tableData,
           testOrderCustomerList: allValues.tablesValue[1].tableData,
         }
       }
    //表单提交事件
    async function requestAddOrEdit(values) {
        try {
            // 预处理日期数据
            changeDateValue(values);
            setModalProps({confirmLoading: true});
            //提交表单
            await saveOrUpdate(values, isUpdate.value);
            //关闭弹窗
            closeModal();
            //刷新列表
            emit('success');
        } finally {
            setModalProps({confirmLoading: false});
        }
    }

    /**
     * 处理日期值
     * @param formData 表单数据
     */
    const changeDateValue = (formData) => {
      if (formData && fieldPickers) {
          for (let key in fieldPickers) {
              if (formData[key]) {
                  formData[key] = getDateByPicker(formData[key], fieldPickers[key]);
              }
          }
      }
      if(formData && formData.testOrderProductList && formData.testOrderProductList.length > 0){
          formData.testOrderProductList.forEach(subFormData=>{
              for (let key in testOrderProductFieldPickers) {
                  if (subFormData[key]) {
                      subFormData[key] = getDateByPicker(subFormData[key], testOrderProductFieldPickers[key]);
                  }
              }
          })
      }
      if(formData && formData.testOrderCustomerList && formData.testOrderCustomerList.length > 0){
          formData.testOrderCustomerList.forEach(subFormData=>{
              for (let key in testOrderCustomerFieldPickers) {
                  if (subFormData[key]) {
                      subFormData[key] = getDateByPicker(subFormData[key], testOrderCustomerFieldPickers[key]);
                  }
              }
          })
      }
    };
</script>

<style lang="less" scoped>
	/** 时间和数字输入框样式 */
  :deep(.ant-input-number) {
    width: 100%;
  }

  :deep(.ant-calendar-picker) {
    width: 100%;
  }
  
  .jeecg-tab {
    padding: 0 20px;
  }
</style>