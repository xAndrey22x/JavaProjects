package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import enums.GiftType;
import exceptions.InvalidInputException;
import model.Gift;
import model.Sender;
import view.GiftsView;
import view.SenderView;

public class Controller {
	private SenderView senderView;
	private GiftsView giftsView;
	private Set<Gift> gifts;

	public Controller(SenderView senderView, GiftsView giftsView, Set<Gift> gifts) {
		this.senderView = senderView;
		this.giftsView = giftsView;
		this.gifts = gifts;

		this.senderView.nextButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = senderView.getNameField();
				String age = senderView.getAgeField();
				String location = senderView.getLocationField();
				boolean isGood = senderView.getIsGood();
				Sender s;
				age = age.trim();
				try {
					s = new Sender(name, age, location, isGood);
					senderView.dispose();
					giftsView.addGiftButtonListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String giftName = giftsView.getGiftName();
							GiftType giftType = giftsView.getGiftType();
							try {
								if (gifts.size() >= 5)
									giftsView.showLimitReachedError("Ati atins maximul de 5 cadouri!");
								else {
									gifts.add(new Gift(giftName, giftType));
									giftsView.addGiftsLabel(gifts);
									giftsView.invalidate();
									giftsView.validate();
									giftsView.repaint();
								}
							} catch (InvalidInputException e2) {
								giftsView.showNameError("Numele cadoului trebuie sa fie de cel putin 10 caractere!");
							}
						}

					});
					giftsView.sendLetterButtonListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (gifts.size() > 0) {
								if (s.isGood())
									giftsView.showSendLetterMessage("Scrisoarea lui " + s.getName() + " din "
											+ s.getLocation() + " avand varsta de " + s.getAge()
											+ " de ani a fost trimisa! Acesta/Aceasta a fost cuminte in acest an!");
								else
									giftsView.showSendLetterMessage("Scrisoarea lui " + s.getName() + " din "
											+ s.getLocation() + " avand varsta de " + s.getAge()
											+ " de ani a fost trimisa! Acesta/Aceasta nu a fost cuminte in acest an!");
								System.exit(0);
							} else
								giftsView.sendLetterError("Nu ati introdus niciun cadou!");
						}

					});
					giftsView.setVisible(true);
				} catch (InvalidInputException e1) {
					if (e1.getMessage().equals("Name"))
						senderView.showNameError("Te rog introdu numele complet! Ar trebui sa contina minim "
								+ "5 caractere si un spatiu, altfel Mos Craciun nu o sa te gaseasca!!");
					if (e1.getMessage().equals("Age"))
						senderView.showAgeError("Te rog introdu o varsta valida! Trebuie sa fie formata din cifre, "
								+ " altfel Mos Craciun nu o sa te gaseasca!!");
					if (e1.getMessage().equals("Location"))
						senderView.showLocationError("Te rog introdu o locatie! Campul trebuie sa fie completat,"
								+ " altfel Mos Craciun nu o sa te gaseasca!!");

				}

			}
		});
	}

}
