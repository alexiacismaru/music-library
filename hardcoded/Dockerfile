FROM postgres:15.2-alpine

ENV POSTGRES_PASSWORD=admin

# Allow connections from outside the container
RUN echo "host all all 127.0.0.1/32 md5" >> /usr/local/share/postgresql/pg_hba.conf
RUN echo "host all all ::1/128      md5" >> /usr/local/share/postgresql/pg_hba.conf
RUN echo "listen_addresses='*'" >> /usr/local/share/postgresql/postgresql.conf

# Create a file to set up the schema and user
RUN mkdir -p /docker-entrypoint-initdb.d
RUN touch /docker-entrypoint-initdb.d/init.sql

# grant full access to 'multitenancy' database to user 'programming5'
RUN echo "CREATE USER programming5 WITH PASSWORD '1234';" >> /docker-entrypoint-initdb.d/init.sql
RUN echo "CREATE DATABASE threading OWNER programming5;" >> /docker-entrypoint-initdb.d/init.sql
RUN echo "GRANT ALL PRIVILEGES ON DATABASE threading TO programming5;" >> /docker-entrypoint-initdb.d/init.sql
