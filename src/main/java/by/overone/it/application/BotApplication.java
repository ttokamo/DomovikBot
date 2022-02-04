package by.overone.it.application;

import by.overone.it.bot.Bot;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@EntityScan("by.overone.it.entity")
@EnableJpaRepositories("by.overone.it.repository")
@ComponentScan("by.overone.it")
public class BotApplication {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(BotApplication.class);
	}

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired TelegramBotsApi telegramBotsApi, @Autowired Bot bot) {
		return args -> {
			telegramBotsApi.registerBot(bot);
		};
	}

	@SneakyThrows
	@Bean
	public TelegramBotsApi telegramBotsApi(Bot bot) {
		return new TelegramBotsApi(DefaultBotSession.class);
	}
}
