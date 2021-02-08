import {Pipe, PipeTransform} from '@angular/core';
import {Order} from '../../entities';

@Pipe({
  name: 'totalPrice'
})
export class TotalPricePipe implements PipeTransform {
  transform(order: Order): number {
    return order.products.reduce((total, product) => {
      total = total + product.price;
      return total;
    }, 0);
  }
}
