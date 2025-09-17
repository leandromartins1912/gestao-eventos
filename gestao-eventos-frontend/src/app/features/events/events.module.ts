import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { EventsRoutes } from './events-routes';


@NgModule({
  declarations: [],
  imports: [
    SharedModule,
    EventsRoutes
  ]
})
export class EventsModule {}
