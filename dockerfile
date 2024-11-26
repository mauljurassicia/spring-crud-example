FROM openjdk:23-bullseye

RUN apt-get update && \
    apt-get install -y \
    git \
    sudo \
    curl \  
    && rm -rf /var/lib/apt/lists

RUN useradd -ms /bin/bash user

RUN echo "user ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

USER user

WORKDIR /home/user/app

COPY . .

RUN sudo chown -R user:user /home/user/app

# Make sure the Maven wrapper is executable
RUN chmod +x ./mvnw

# Run Maven to build the application
RUN ./mvnw clean install

# Expose port 8080 for the application
EXPOSE 8080

# Command to run the application
CMD ["./mvnw", "spring-boot:run"]