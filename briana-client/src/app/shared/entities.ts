export interface Described {
  name: string;
  description?: string;
}

export interface Employee extends Described {
  readonly id?: number;
  name: string;
  authorities?: string[];
  readonly organizationId?: number;
  password?: string;
  enabled?: boolean;
  phone: string;
  email: string;
  address: Address;
  positionName: string;
}

export interface Position extends Described {
  readonly id?: number;
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
  id: string;
  name: string;
  imageSrc?: string;
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

export interface Authenticated {
  employeeId: number;
  organizationId: number;
  authorities: string[];
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

