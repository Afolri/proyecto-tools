FROM node:lts-bullseye AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build -- --configuration=production

FROM nginx:alpine
COPY ./config/default.conf /etc/nginx/conf.d/default.conf
COPY ./www.emultitools.local.pem /etc/nginx/ssl/www.emultitools.local.pem
COPY ./www.emultitools.local-key.pem /etc/nginx/ssl/www.emultitools.local-key.pem
COPY --from=build /app/dist/multi-tools/browser/ /var/www/app/
RUN chown -R nginx:nginx /var/www/app
EXPOSE 443
CMD ["nginx", "-g", "daemon off;"]
