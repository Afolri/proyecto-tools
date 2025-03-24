FROM node:lts-bullseye AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build -- --configuration=production

FROM nginx:alpine
COPY ./config/default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/multi-tools/browser/ /var/www/app/
RUN chown -R nginx:nginx /var/www/app
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
