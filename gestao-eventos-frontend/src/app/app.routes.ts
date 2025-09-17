import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'events',
    loadChildren: () =>
      import('./features/events/events-routes').then(m => m.routes)
  }
];
