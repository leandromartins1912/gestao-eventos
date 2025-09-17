import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environment/enviroment';

export interface EventDto {
  id?: number;
  title: string;
  description: string;
  location: string;
  eventDatetime: string;
}

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  list(page = 0, size = 10): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  get(id: number): Observable<EventDto> {
    return this.http.get<EventDto>(`${this.apiUrl}/${id}`);
  }

  create(event: EventDto): Observable<EventDto> {
    return this.http.post<EventDto>(this.apiUrl, event);
  }

  update(id: number, event: EventDto): Observable<EventDto> {
    return this.http.put<EventDto>(`${this.apiUrl}/${id}`, event);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
