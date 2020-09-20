import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

export interface Message {
    response: string
}

@Injectable({
    providedIn: 'root'
})
export class TestService {

    constructor(private http: HttpClient) { }

    getMessage() : Observable<Message> {
        return this.http.get<Message>("/api");
    }
}
