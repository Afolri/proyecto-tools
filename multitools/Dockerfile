FROM node:lts-bullseye AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build -- --configuration=production

FROM nginx:alpine
COPY ./config/default.conf /etc/nginx/conf.d/default.conf
COPY ./apache-selfsigned.crt /etc/nginx/ssl/apache-selfsigned.crt
COPY ./apache-selfsigned.key /etc/nginx/ssl/apache-selfsigned.key
COPY --from=build /app/dist/multi-tools/browser/ /var/www/app/
RUN chown -R nginx:nginx /var/www/app
EXPOSE 443
CMD ["nginx", "-g", "daemon off;"]
