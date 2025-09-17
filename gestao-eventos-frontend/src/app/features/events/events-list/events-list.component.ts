import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableDataSource } from '@angular/material/table';

import { EventService, EventDto  } from '../../../core/services/event.service';

import { SpinnerComponent } from '../../../shared/shared/components/spinner/spinner.component';

@Component({
  selector: 'app-events-list',
  standalone: true,
  templateUrl: './events-list.component.html',
  styleUrls: ['./events-list.component.css'],
  imports: [
    CommonModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
    SpinnerComponent
  ]
})
export class EventsListComponent implements OnInit {
  dataSource = new MatTableDataSource<EventDto>([]);
  events: EventDto[] = [];
  page = 0;
  size = 10;
  total = 0;
  loading = false;

  displayedColumns = ['title', 'eventDatetime', 'location', 'actions'];

  constructor(private router: Router, private eventService: EventService, private titleService: Title) {}

  ngOnInit() {
    this.titleService.setTitle('Gestão Eventos - Lista');
    this.loadEvents();
  }

  editEvent(id: number) {
    this.router.navigate(['/events', id, 'edit']);
  }

  navegarParaDetalhes(id: number) {
    this.router.navigate(['/events', id]);
  }

  loadEvents() {
    this.loading = true;
    this.eventService.list(this.page, this.size).subscribe({
      next: resp => {
        // this.events = resp.content;
        this.dataSource.data = resp.content;
        this.total = resp.totalElements;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  onPageChange(event: any) {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.loadEvents();
  }
}
