import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface ITask {
    response: string
}

@Injectable({
    providedIn: 'root'
})
export class BookService {

    constructor(private http: HttpClient) { }


    getHello() : Observable<ITask> {
        return this.http.get<ITask>("/api");
    }
}
