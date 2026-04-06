import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import {JVxeTypes,JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '订单编码',
    align:"center",
    dataIndex: 'orderCode'
   },
   {
    title: '下单时间',
    align:"center",
    dataIndex: 'orderDate',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
   },
   {
    title: '描述',
    align:"center",
    dataIndex: 'descc'
   },
   {
    title: '下拉多选',
    align:"center",
    dataIndex: 'xiala_dictText'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "订单编码",
      field: "orderCode",
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "下单时间",
      field: "orderDate",
      component: 'DatePicker',
      componentProps: {
        valueFormat: 'YYYY-MM-DD'
      },      
      //colProps: {span: 6},
 	},
	{
      label: "下拉多选",
      field: "xiala",
      component: 'JSelectMultiple',
      componentProps:{
           dictCode:"sex",
           triggerChange: true
      },
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '订单编码',
    field: 'orderCode',
    component: 'Input',
  },
  {
    label: '下单时间',
    field: 'orderDate',
    component: 'DatePicker',
    componentProps:{
      valueFormat: 'YYYY-MM-DD'
    },    
  },
  {
    label: '描述',
    field: 'descc',
    component: 'Input',
  },
  {
    label: '下拉多选',
    field: 'xiala',
    component: 'JSelectMultiple',
    componentProps:{
        dictCode:"sex"
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
//子表单数据
//子表表格配置
export const testOrderProductColumns: JVxeColumn[] = [
    {
      title: '产品名字',
      key: 'productName',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '价格',
      key: 'price',
      type: JVxeTypes.inputNumber,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
        validateRules: [
          { required: false},
          { pattern: "n", message: "${title}格式不正确" }
        ],
    },
    {
      title: '数量',
      key: 'num',
      type: JVxeTypes.inputNumber,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
        validateRules: [
          { required: false},
          { pattern: "n", message: "${title}格式不正确" }
        ],
    },
    {
      title: '产品类型',
      key: 'proType',
      type: JVxeTypes.select,
      options:[],
      dictCode:"sex",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '描述',
      key: 'descc',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]
export const testOrderCustomerColumns: JVxeColumn[] = [
    {
      title: '客户名字',
      key: 'name',
      type: JVxeTypes.input,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '性别',
      key: 'sex',
      type: JVxeTypes.select,
      options:[],
      dictCode:"sex",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '年龄',
      key: 'age',
      type: JVxeTypes.inputNumber,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '生日',
      key: 'birthday',
      type: JVxeTypes.date,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '地址',
      key: 'address',
      type: JVxeTypes.pca,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]


// 高级查询数据
export const superQuerySchema = {
  orderCode: {title: '订单编码',order: 0,view: 'text', type: 'string',},
  orderDate: {title: '下单时间',order: 1,view: 'date', type: 'string',},
  descc: {title: '描述',order: 2,view: 'text', type: 'string',},
  xiala: {title: '下拉多选',order: 3,view: 'list_multi', type: 'string',dictCode: 'sex',},
  //子表高级查询
  testOrderProduct: {
    title: '订单产品明细',
    view: 'table',
    fields: {
        productName: {title: '产品名字',order: 0,view: 'text', type: 'string',},
        price: {title: '价格',order: 1,view: 'number', type: 'number',},
        num: {title: '数量',order: 2,view: 'number', type: 'number',},
        proType: {title: '产品类型',order: 3,view: 'radio', type: 'string',dictCode: 'sex',},
        descc: {title: '描述',order: 4,view: 'text', type: 'string',},
    }
  },
  testOrderCustomer: {
    title: '订单客户',
    view: 'table',
    fields: {
        name: {title: '客户名字',order: 0,view: 'text', type: 'string',},
        sex: {title: '性别',order: 1,view: 'radio', type: 'string',dictCode: 'sex',},
        age: {title: '年龄',order: 2,view: 'number', type: 'number',},
        birthday: {title: '生日',order: 3,view: 'date', type: 'string',},
        address: {title: '地址',order: 4,view: 'pca', type: 'string',},
    }
  },
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
// 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}