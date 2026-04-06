import { BasicColumn, FormSchema } from '/@/components/Table';

// 列表页展示的列 [cite: 1, 5, 10-11]
export const columns: BasicColumn[] = [
  { title: '样品编号', dataIndex: 'sampleNo', width: 120 },
  { title: '样品名称', dataIndex: 'sampleName', width: 150 },
  { title: '试验名称', dataIndex: 'experimentName', width: 180 },
  { title: '试验日期', dataIndex: 'experimentDate', width: 120 },
  { title: '试验者', dataIndex: 'tester', width: 100 },
];

// 搜索表单配置
export const searchFormSchema: FormSchema[] = [
  { label: '样品名称', field: 'sampleName', component: 'Input' },
  { label: '试验日期', field: 'experimentDate', component: 'RangePicker' },
];

// 填报表单配置（纸质化排版） [cite: 1, 2, 5, 10-11]
export const formSchema: FormSchema[] = [
  // --- 上半部分 ---
  { label: '样品编号', field: 'sampleNo', component: 'Input', colProps: { span: 8 } },
  { label: '样品名称', field: 'sampleName', component: 'Input', colProps: { span: 8 } },
  { label: '品牌及成分', field: 'drugBrandAndComposition', component: 'Input', colProps: { span: 8 } },
  { label: '试验名称', field: 'experimentName', component: 'Input', colProps: { span: 12 } },
  { label: '试验日期', field: 'experimentDate', component: 'DatePicker', colProps: { span: 12 } },
  {
    label: '试验动物',
    field: 'testAnimal',
    component: 'JDictSelectTag',
    componentProps: { dictCode: 'vbd_monitor_data_test_animal' },
    colProps: { span: 8 },
  },
  { label: '动物来源', field: 'testAnimalSource', component: 'Input', colProps: { span: 8 } },
  { label: '试验装置', field: 'testDevice', component: 'Input', colProps: { span: 8 } },
  { label: '试验过程', field: 'experimentProcess', component: 'InputTextArea', colProps: { span: 24 } },

  // --- 中间部分：插槽占位 ---
  {
    field: 'subTableSlot',
    label: '',
    component: 'Input',
    slot: 'paperSubTable',
    colProps: { span: 24 }, // 占据整行
    // 👉 加上下面这行！强制清空这一个字段的左侧 label 占位宽度
    labelWidth: '0px',
    itemProps: {
      wrapperCol: { span: 24 }, // 强制内容区域占用 100%
    },
  },
  // --- 下半部分 ---
  { label: '死亡率(%)', field: 'mortalityRate', component: 'InputNumber', colProps: { span: 12 } },
  { label: '捕杀率(%)', field: 'killRate', component: 'InputNumber', colProps: { span: 12 } },
  { label: '校正死亡率(%)', field: 'correctedMortalityRate', component: 'InputNumber', colProps: { span: 12 } },
  { label: '校正捕杀率(%)', field: 'correctedKillRate', component: 'InputNumber', colProps: { span: 12 } },
  { label: '试验者', field: 'tester', component: 'Input', colProps: { span: 12 } },
  { label: '复核者', field: 'reviewer', component: 'Input', colProps: { span: 12 } },

  {
    label: '填报日期',
    field: 'testerDate',
    component: 'DatePicker',
    componentProps: { valueFormat: 'YYYY-MM-DD', style: { width: '100%' } },
    colProps: { span: 12 },
  },
  {
    label: '复核日期',
    field: 'reviewDate',
    component: 'DatePicker',
    componentProps: { valueFormat: 'YYYY-MM-DD', style: { width: '100%' } },
    colProps: { span: 12 },
  },
];
