import { ref } from 'vue';

import type { Router } from 'vue-router';

export const useRouterTransition = (router: Router) => {
  const routerTransitionAnimation = ref('');
  
  router.beforeResolve(
    (to, from) => {
      const toIndex = to.meta.index as unknown as number;
      const fromIndex = from.meta.index as unknown as number;
      if(toIndex > fromIndex) routerTransitionAnimation.value = 'slide-up';
      else routerTransitionAnimation.value = 'slide-down';
      return true;
    }
  )

  return routerTransitionAnimation;
}