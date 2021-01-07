export interface TranslationToken {
  [key: string]: {} | string;
}

export const AVAILABLE_LANGUAGES = [
  {id: 'en', name: 'English'},
  {id: 'ru', name: 'Русский'},
  {id: 'uk', name: 'Ukraine'},
];

export const DEFAULT_LANGUAGE = 'en';

export const LANGUAGES: TranslationToken = {
  default: 'en',
  login: {
    en: 'Login',
    ru: 'Логин'
  },
  ds: {
    en: 'Dashboard',
    ru: 'Главная'
  },
  an: {
    en: 'Analytics',
    ru: 'Аналитика'
  },
  ev: {
    en: 'Events',
    ru: 'События'
  },
  hs: {
    en: 'History',
    ru: 'История'
  },
  ct: {
    en: 'Categories',
    ru: 'Ассортимент'
  },
  em: {
    en: 'Employees',
    ru: 'Сотрудники'
  },
  cl: {
    en: 'Clients',
    ru: 'Клиенты'
  },
  st: {
    en: 'Settings',
    ru: 'Настройки'
  },
  password: {
    en: 'Password',
    ru: 'Пароль'
  },
  save_up: {
    en: 'Update profile',
    ru: 'Сохранить изменения'
  },
  repeat_pass: {
    en: 'Repeat password',
    ru: 'Повторите пароль'
  },
  new_pass: {
    en: 'New password',
    ru: 'Новый пароль'
  },
  name: {
    en: 'Name',
    ru: 'Имя'
  },
  upload: {
    en: 'Upload',
    ru: 'Загрузить'
  },
  exit: {
    en: 'Log out',
    ru: 'Выйти'
  },
  pnm: {
    en: 'Passwords not match',
    ru: 'Пароли не совпадают'
  },
  pmn: {
    en: 'Minimal password length:',
    ru: 'Минимальная длина пароля:'
  },
  chooseLang: {
    en: 'Language',
    ru: 'Язык системы'
  },
  upPass: {
    en: 'Update password',
    ru: 'Обновить пароль'
  },
  country: {
    en: 'Country',
    ru: 'Страна'
  },
  city: {
    en: 'City',
    ru: 'Город'
  },
  postcode: {
    en: 'Postcode',
    ru: 'Почтовый индекс'
  },
  edit: {
    en: 'Edit',
    ru: 'Изменить'
  },
  rm: {
    en: 'Remove',
    ru: 'Удалить'
  },
  bio: {
    en: 'Bio',
    ru: 'О себе'
  },
  profile: {
    en: 'Public profile',
    ru: 'Публичный профиль'
  },
  chgPass: {
    en: 'Change password',
    ru: 'Изменить пароль'
  },
  rmQue: {
    en: 'Do you want to remove the image?',
    ru: 'Вы хотите удалить изображение?'
  },
  save: {
    en: 'Save',
    ru: 'Сохранить'
  },
  more: {
    en: 'More',
    ru: 'Ещё'
  },
  overview: {
    en: 'Overview',
    ru: 'Обзор'
  },
  orders: {
    en: 'Orders',
    ru: 'Заказы'
  },
  create: {
    en: 'Create',
    ru: 'Создать'
  },
  email: {
    en: 'Email',
    ru: 'Email',
  },
  phone: {
    en: 'Phone',
    ru: 'Телефон'
  },
  close: {
    en: 'Close',
    ru: 'Закрыть'
  },
  desc: {
    en: 'Description',
    ru: 'Описание'
  },
  at: {
    en: 'at',
    ru: 'в'
  }
};
