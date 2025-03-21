FROM node:23

WORKDIR /app

COPY package*.json ./

RUN npm ci -g @angular/cli

RUN npm ci

CMD ["ng", "serve", "--host", "0.0.0.0"]