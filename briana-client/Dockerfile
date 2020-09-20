FROM nginx:latest

COPY nginx/nginx.conf /etc/nginx/nginx.conf

RUN rm -rf /usr/share/nginx/html/*

COPY ./dist/briana-client /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
