import { useCookies } from '@vueuse/integrations/useCookies';
import { ElMessage } from 'element-plus';

import type { Router } from 'vue-router';

export const useAuthorization = (router: Router) => {
  const cookies = useCookies();

  router.beforeEach(
    (to, from, next) => {
      const token = cookies.get('token');
      if(to.path !== '/' && !token) {
        ElMessage({
          type: 'error',
          message: '你还没有登录'
        });
        next({ path: '/'} );
      }
      else next();
    }
  )
}