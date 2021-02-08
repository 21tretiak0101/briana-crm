import {ElementRef} from '@angular/core';

declare var M;

export interface MaterialInstance {
  open?(): void;
  close?(): void;
  destroy?(): void;
}

export interface Datepicker extends MaterialInstance {
  date?: Date;
}

export interface Tabs extends MaterialInstance {
  index: number;
  updateTabIndicator(): void;
  select(el: string): void;
}

export class MaterialService {
  static toast(message: string) {
    M.toast({
      html: message,
      classes: 'rounded'
    });
  }

  static error(message: string) {
    M.toast({html: message, classes: 'rounded red-text'});
  }

  static updateInputs() {
    M.updateTextFields();
  }

  static initModal(ref: ElementRef): MaterialInstance {
    return M.Modal.init(ref.nativeElement, {
      dismissible: false
    });
  }

  static initDatepicker(ref: ElementRef, onClose: () => void): Datepicker {
    return M.Datepicker.init(ref.nativeElement, {
      format: 'dd.mm.yyyy',
      showClearBtn: true,
      onClose
    });
  }

  static initSelect(ref: ElementRef): MaterialInstance {
    return M.FormSelect.init(ref.nativeElement);
  }

  static initTabs(ref: ElementRef): Tabs {
    return M.Tabs.init(ref.nativeElement);
  }

  static initCollapsible(ref: ElementRef) {
    M.Collapsible.init(ref.nativeElement);
  }

  static chips(
    ref: ElementRef,
    data: string[],
    placeholder: string,
    secondaryPlaceholder: string
  ): void {
    M.Chips.init(ref.nativeElement, {
      placeholder,
      secondaryPlaceholder,
      data: data.map((el) => ({tag: el})),
      autocompleteOptions: {
        data: data.reduce((tags, el) => {
          tags[el] = null;
          return tags;
        }, {}),
        minLength: 1
      },
      hasAutocomplete: true
    });
  }
}
