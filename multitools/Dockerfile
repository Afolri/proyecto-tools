
FROM node:lts-bullseye as build
WORKDIR /app
COPY package*.json ./
RUN npm install -g @angular/cli
RUN npm ci
COPY . .
RUN npm run build


FROM nginx:alpine
ADD ./config/default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/multitools/browser/ /var/www/app/
RUN chown -R nginx:nginx /var/www/app
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
