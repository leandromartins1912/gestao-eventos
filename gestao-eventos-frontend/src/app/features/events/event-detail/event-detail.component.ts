import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router ,ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { EventService, EventDto } from '../../../core/services/event.service';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-event-detail',
  standalone: true,
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.css'],
  imports: [
    CommonModule,
    MatPaginatorModule,
    MatTableModule,
    MatCardModule
  ]
})
export class EventDetailComponent implements OnInit {
  event?: EventDto;

  constructor(private router: Router,private route: ActivatedRoute, private eventService: EventService, private titleService: Title) {}

  ngOnInit() {
    this.titleService.setTitle('Gestão Eventos - Editar');
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.eventService.get(id).subscribe(event => this.event = event);
  }

  voltarLista() {
    this.router.navigate(['/events']);
  }
}
