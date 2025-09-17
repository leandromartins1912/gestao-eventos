import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { EventService, EventDto } from '../../../core/services/event.service';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-event-form',
  standalone: true,
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.css'],
  imports: [
      CommonModule,
      ReactiveFormsModule,
      MatFormFieldModule,
      MatPaginatorModule,
      MatTableModule,
      MatInputModule,
      MatButtonModule
    ]
})
export class EventFormComponent implements OnInit {
  form!: FormGroup;
  editMode = false;
  loading = false
  errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private eventService: EventService,
    private route: ActivatedRoute,
    private router: Router,
    private titleService: Title
  ) {}

  ngOnInit() {
    this.form = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      location: ['', Validators.required],
      eventDatetime: ['', Validators.required],
    });


    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.titleService.setTitle('Gestão Eventos - Editar');
      this.editMode = true;
      this.eventService.get(+id).subscribe(event => this.form.patchValue(event));
    }else {
      this.titleService.setTitle('Gestão Eventos - Adicionar');
    }
  }

  onSubmit() {
  if (this.form.invalid) return;

  const formValue = this.form.value as EventDto;
  this.errorMessage = null; // resetar mensagem antes

  const action$ = this.editMode
    ? this.eventService.update(+this.route.snapshot.paramMap.get('id')!, formValue)
    : this.eventService.create(formValue);

    action$.subscribe({
      next: () => this.router.navigate(['/events']),
      error: (errorResponse) => {
        console.log('Erro', errorResponse)
        this.errorMessage = typeof errorResponse.error === 'string'
          ? errorResponse.error
          : 'Erro desconhecido no servidor.';
        }
    });
  }
}
