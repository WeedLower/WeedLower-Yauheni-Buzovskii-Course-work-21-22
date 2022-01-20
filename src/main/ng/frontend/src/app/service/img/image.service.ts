import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ImageModel} from "../../model/Image";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http:HttpClient) { }

  public list(id:number):Observable<ImageModel>{
    return this.http.get<ImageModel>("/api/cloudinary/"+id);
  }

  public upload(image: File): Observable<ImageModel>{
    const formData = new FormData();
    formData.append('multipartFile',image);
    return this.http.post<ImageModel>("api/cloudinary/upload",formData);
  }

  public delete(id: number):Observable<any>{
  return this.http.delete<any>("api/cloudinary/"+id);
  }
}
