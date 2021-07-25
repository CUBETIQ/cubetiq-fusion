import { Flow } from "@vaadin/flow-frontend";
import { Route } from '@vaadin/router';
import Role from './generated/com/cubetiqs/fusion/data/Role';
import { appStore } from './stores/app-store';
import './views/home/home-view';
import './views/main-layout';

const { serverSideRoutes } = new Flow({
  imports: () => import('../target/frontend/generated-flow-imports'),
});

export type ViewRoute = Route & {
  title?: string;
  icon?: string;
  requiresLogin?: boolean;
  rolesAllowed?: Role[];
  children?: ViewRoute[];
};

export const hasAccess = (route: Route) => {
  const viewRoute = route as ViewRoute;
  if (viewRoute.requiresLogin && !appStore.loggedIn) {
    return false;
  }

  if (viewRoute.rolesAllowed) {
    return viewRoute.rolesAllowed.some((role) => appStore.isUserInRole(role));
  }
  return true;
};

export const views: ViewRoute[] = [
  // place routes below (more info https://vaadin.com/docs/latest/fusion/routing/overview)
  {
    path: '',
    component: 'home-view',
    icon: 'la la-home',
    title: 'Home',
  },
  {
    path: 'profile',
    component: 'profile-view',
    requiresLogin: true,
    icon: 'la la-user',
    title: 'Profile',
    action: async (_context, _command) => {
      if (!hasAccess(_context.route)) {
        return _command.redirect('login');
      }
      await import('./views/profile/profile-view');
      return;
    },
  },
  {
    path: 'admin',
    component: 'admin-view',
    rolesAllowed: [Role.ADMIN],
    icon: 'la la-user-lock',
    title: 'Admin',
    action: async (_context, _command) => {
      if (!hasAccess(_context.route)) {
        return _command.redirect('login');
      }
      await import('./views/admin/admin-view');
      return;
    },
  },
];
export const routes: ViewRoute[] = [
  {
    path: '',
    component: 'main-layout',
    children: [
        ...views,
      // for server-side, the next magic line sends all unmatched routes:
      ...serverSideRoutes, // IMPORTANT: this must be the last entry in the array
    ],
  },
  {
    path: 'login',
    component: 'login-view',
    icon: '',
    title: 'Login',
    action: async (_context, _command) => {
      await import('./views/login/login-view');
      return;
    },
  },
];
