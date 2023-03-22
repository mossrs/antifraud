import { logout as httpLogout } from './use-http';
import { useCookies } from '@vueuse/integrations/useCookies';
import { ElMessage } from 'element-plus';

import type { Result } from './use-http';
import type { Router } from 'vue-router';


export const useLogout = (router: Router) => {
  const cookies = useCookies(['locale']);
  const logout = () => {
    httpLogout().then(response => {
      const res: Result = response.data;
      if(res.code === 1) {
        cookies.remove('token');
        router.replace('/');
        ElMessage({ type: 'success', message: '登出成功' });
      } else ElMessage({ type: 'error', message: res.msg });
    }).catch(() => ElMessage({ type: 'error', message: '网络异常' }));
  };
  return { logout };
};