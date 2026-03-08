# Stage 1: Build
FROM node:20-alpine AS build
WORKDIR /app

COPY package.json package-lock.json* ./
RUN npm install

COPY . .

# Build args for Vite (build-time env vars)
ARG VITE_SERVER_URL
ARG VITE_SOCKET_URL
ARG VITE_CLIENT_URL
ARG VITE_CLIENT_ID
ARG VITE_GEMINI_API_KEY
ARG VITE_GEMINI_URL

RUN npm run build

# Stage 2: Serve with nginx
FROM nginx:alpine

COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]
