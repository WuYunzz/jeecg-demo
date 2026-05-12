import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '患者姓名',
    align:"center",
    dataIndex: 'patientName'
   },
   {
    title: '身份证号',
    align:"center",
    dataIndex: 'idCard'
   },
   {
    title: '现住址',
    align:"center",
    dataIndex: 'presentAddress'
   },
   {
    title: '报卡日期',
    align:"center",
    dataIndex: 'reportDate',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
   },
   {
    title: '报卡单位',
    align:"center",
    dataIndex: 'reportOrg'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
  {
    label: "患者姓名",
    field: "patientName",
    component: 'JInput',
  },
  {
    label: "身份证号",
    field: "idCard",
    component: 'JInput',
  },
     {
      label: "发病日期",
      field: "onsetDate",
      component: 'RangePicker',
      componentProps: {
        valueType: 'Date',
      },
      //colProps: {span: 6},
	},
     {
      label: "报卡日期",
      field: "reportDate",
      component: 'RangePicker',
      componentProps: {
        valueType: 'Date',
      },
      //colProps: {span: 6},
	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '患者姓名',
    field: 'patientName',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入患者姓名!'},
          ];
     },
  },
  {
    label: '身份证号',
    field: 'idCard',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入身份证号!'},
          ];
     },
  },
  {
    label: '性别',
    field: 'patientGender',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入性别!'},
          ];
     },
  },
  {
    label: '患者生日',
    field: 'patientBirthday',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入患者生日!'},
          ];
     },
  },
  {
    label: '现住址',
    field: 'presentAddress',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入现住址!'},
          ];
     },
  },
  {
    label: '发病日期',
    field: 'onsetDate',
    component: 'DatePicker',
    componentProps: {
      valueFormat: 'YYYY-MM-DD'
    },
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入发病日期!'},
          ];
     },
  },
  {
    label: '发病年龄',
    field: 'onsetAge',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入发病年龄!'},
          ];
     },
  },
  {
    label: '报卡日期',
    field: 'reportDate',
    component: 'DatePicker',
    componentProps: {
      valueFormat: 'YYYY-MM-DD'
    },
  },
  {
    label: '报卡单位',
    field: 'reportOrg',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入报卡单位!'},
          ];
     },
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

// 高级查询数据
export const superQuerySchema = {
  patientName: {title: '患者姓名',order: 0,view: 'text', type: 'string',},
  idCard: {title: '身份证号',order: 1,view: 'text', type: 'string',},
  presentAddress: {title: '现住址',order: 4,view: 'text', type: 'string',},
  reportDate: {title: '报卡日期',order: 7,view: 'date', type: 'string',},
  reportOrg: {title: '报卡单位',order: 8,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}