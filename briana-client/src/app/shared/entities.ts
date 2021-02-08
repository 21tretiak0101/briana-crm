export interface Described {
  name: string;
  description?: string;
}

export interface Employee extends Described {
  readonly id?: number;
  email: string;
  positionName?: string;
  position?: Position;
  readonly organizationId?: number;
  authorities?: string[];
  password?: string;
  enabled?: boolean;
  phone?: string;
  address?: Address;
  imgSrc?: string;
}

export interface Position extends Described {
  readonly id?: string;
  permissions?: string[];
}

export interface Address {
  country: string;
  city: string;
  postcode: string;
}

export interface Owner {
    name: string;
    phone: string;
    email: string;
    password: string;
    organizationName: string;
    organizationCurrency: string;
    organizationDescription: string;
}

export interface Category {
  id?: string;
  name: string;
  imageSrc?: string;
  description?: string;
}

export interface Product {
  id?: string;
  name: string;
  price: number;
  imageSrc?: string;
  description?: string;
  category?: Category;
  categoryId?: string;
}

export interface Order {
  id: number;
  date: Date;
  products: Product[];
}

export interface Filter {
  start?: Date;
  end?: Date;
  order?: number;
}

export interface Client {
  id?: number;
  name: string;
  email: string;
  phone: string;
  orders?: Order[];
  address?: Address;
  description?: string;
}

export interface Event {
  type: EventType;
  message: string;
  publisherId: number;
  publisherName: string;
  published: Date;
}

export enum EventType {
  ADD = 'add',
  UPDATE = 'update',
  DELETE = 'delete'
}
