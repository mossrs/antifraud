import { studentLogin, adminLogin } from './use-http';
import { ref, computed, getCurrentInstance } from 'vue';
import { ElMessage } from 'element-plus';
import { useCookies } from '@vueuse/integrations/useCookies';

import type { Result } from './use-http';
import type { Ref, ComputedRef } from 'vue';
import type { AxiosResponse } from 'axios';

type LoginMethod = typeof studentLogin;

interface Identitify {
  loginMethod: LoginMethod;
  toPath: string;
  requestBody: object
};

export const useLogin = (identity?: Ref<string>) => {
  const username = ref('');
  const password = ref('');
  const identitify: ComputedRef<Identitify> = computed(() => {
    return identity!.value === '学生'
      ? {
        loginMethod: studentLogin,
        toPath: '/home',
        requestBody: {
          studentCard: username.value,
          password: password.value
        }
      }
      : {
        loginMethod: adminLogin,
        toPath: '/admin',
        requestBody: {
          adminCard: username.value,
          password: password.value
        }
      };
  });

  const cookies = useCookies(['locale']);
  const ctx = getCurrentInstance();

  const login = () => {
    const {
      loginMethod,
      toPath,
      requestBody
    } = identitify.value;

    loginMethod({
      data: requestBody
    }).then((response: AxiosResponse) => {
      const { data } = response;
      const res: Result = data;
      if (res.code === 1) {
        const expires = new Date();
        expires.setDate(expires.getDate() + 7);
        cookies.set('token', res.data, { expires });
        ElMessage({
          type: 'success',
          message: '登录成功'
        });
        ctx!.emit('router-push', toPath);
      } else {
        ElMessage({
          type: 'error',
          message: '账号或密码错误'
        });
      }
    });
  }

  return {
    username,
    password,
    login
  };
};