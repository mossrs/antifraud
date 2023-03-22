import axios from 'axios';
import { useCookies } from '@vueuse/integrations/useCookies';
import { ElMessage } from 'element-plus';

import type { AxiosRequestConfig } from 'axios';

export type { AxiosResponse } from 'axios';

export const BASE_URL = 'http://101.35.247.14:8081/antifraud/';

export interface GetParams {
  data?: undefined | number | string | object | number[] | string[] | object[];
  config?: AxiosRequestConfig;
};

export interface PostParams {
  data: undefined | number | string | object | number[] | string[] | object[],
  config?: AxiosRequestConfig
};
export interface GetParams1 {
  data: undefined | number | string | object | number[] | string[] | object[],
  config?: AxiosRequestConfig
};

export interface ResolvedGetParams extends GetParams {
  path: string;
};
export interface ResolvedGetParams1 extends GetParams1 {
  path: string;
};

export interface ResolvedPostParams extends PostParams {
  path: string;
};

export interface Result {
  code: number;
  msg: string;
  data: number | object | string | object[] | string[] | number[];
};


/**
 * DONE: 构建请求拦截器
 */

const cookies = useCookies(['locale']);

axios.interceptors.request.use(config => {
  const token = cookies.get('token');
  if(token) config.headers!['token'] = token;
  return config;
}, () => ElMessage({ type: 'error', message: '网络异常，请检查网络设置' }));

export const put = (params: ResolvedPostParams) => {
  const { path, data, config } = params;
  return axios.put(`${BASE_URL}${[path]}`, data, config);
}

export const del = (params: ResolvedGetParams) => {
  const { path, config } = params;
  return axios.delete(`${BASE_URL}${[path]}`, config);
}

export const get = (params: ResolvedGetParams) => {
  const { data, path, config } = params;
  return axios.get(`${BASE_URL}${path}`, {
    ...config,
    params: data
  });
};
// export const get1 = (params: ResolvedGetParams1) => {
//   const { path, data, config } = params;
//   return axios.get(`${BASE_URL}${path}`, data, config);
// };

export const post = (params: ResolvedPostParams) => {
  const { path, data, config } = params;
  const contentType = config?.headers!['Content-Type'] || config?.headers!['content-type'];
  if (contentType) {
    const urlEncodedData = new URLSearchParams();
    Object.entries(data as object).forEach(([key, val]) => urlEncodedData.append(key, val));
    return axios.post(`${BASE_URL}${path}`, urlEncodedData, { ...config || {} });
  }
  return axios.post(`${BASE_URL}${path}`, data, { ...config || {} });
};


export const studentLogin = (params: PostParams) =>
  post({
    ...params,
    path: 'students/login' ,
    config: {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }
  });

export const adminLogin = (params: PostParams) => 
  post({ 
    ...params,
    path: 'admins/login' ,
    config: {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }
  });



export const getAllQuizs = (params?: GetParams) =>
  get({ ...params, path: 'admins/fraudSubject/list' });

export const updateQuizItem = (params: PostParams) =>
  put({ ...params, path: 'admins/fraudSubject/update' });

export const deleteQuizItem = (id: number) =>
  del({ path: `admins/fraudSubject/delete/${id}` });

export const getAllFraudTypes = (params?: GetParams) =>
  get( { ...params, path: 'admins/fraudType/list' });

export const addQuizItem = (params: PostParams) =>
  post({ ...params, path: 'admins/fraudSubject/save' });

export const batchDeleteQuizItems = (params: PostParams) =>
  del({ ...params, path: 'admins/fraudSubject/deleteBatch' });

export const logout = (params?: GetParams) =>
  get({ ...params, path: 'admins/logout' });

export const getAllTags = (params?: GetParams) =>
  get({ ...params, path: 'admins/fraudTag/list' });

export const getTotalNumber = (params?: GetParams) =>
  get({ ...params, path: 'admins/statistics/cs' });

export const getDefraudedNumber = (params?: GetParams) =>
  get({ ...params, path: 'admins/statistics/cfsu' });

export const getPushedNumber = (params?: GetParams) =>
  get({ ...params, path: 'admins/statistics/cfst' });

export const getCompletedNumber = (params?: GetParams) =>
  get({ ...params, path: 'admins/statistics/cffs' });
  
export const getEachFraudTypesWarning = (params?: GetParams) =>
  get({ ...params, path: 'admins/statistics/cfl' });

export const getQuizTrend = (params?: GetParams) =>
  get({ ...params, path: 'admins/statistics/cmfst' });

export const getEachFraudTypesDefraudedNumbers = (params?: GetParams) =>
  get({ ...params, path: 'admins/statistics/cfsr' });

export const getEachFraudTypesDefraudedRatio = (params?: GetParams) =>
  get({ ...params, path: 'admins/statistics/cfafr' });

export const getAllClasses = (params?: GetParams) =>
  get({ ...params, path: 'admins/stuClazz/list' });

export const getAllUsers = (params: GetParams) =>
  get({ ...params, path: 'admins/stu/list' });

export const setPersonaData = (params: PostParams) =>
  post({ ...params, path: 'stus/fraudAssessment/save' });

export const getScene02Test = (params?: GetParams) =>
  get({ ...params, path: 'stus/fraudSubject/list' });

export const getPersonaByStuNumber = (params: GetParams) =>
  get({ ...params, path: 'admin/portrait/get' });

export const addFraudTag = (params: PostParams) =>
  post({ ...params, path: 'admins/fraudTag/save' });

export const getStudentInfo = (params?: GetParams) => 
  get({ ...params, path: 'students/getInfo' });