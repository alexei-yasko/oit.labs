package yaskoam.oit.lab2.web.car;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.BufferedDynamicImageResource;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.IResource;

import yaskoam.oit.lab2.service.model.Car;
import yaskoam.oit.lab2.web.BasePage;

public class CarsPage extends BasePage<List<Car>> {

    public CarsPage() {
        setModel(new CarsModel());

        DataView<Car> dataView = new DataView<Car>("cars", new CarsDataProvider(getModel())) {
            @Override
            protected void populateItem(Item<Car> item) {
                item.add(new Label("code", PropertyModel.of(item.getModel(), "code")));
                item.add(new Label("model", PropertyModel.of(item.getModel(), "model")));
                item.add(new Image("photo", createImageResource(item.getModelObject().getPhoto())));

                item.add(new Link<Car>("editLink", item.getModel()) {
                    @Override
                    public void onClick() {

                    }
                });

                item.add(new Link<Car>("removeLink", item.getModel()) {
                    @Override
                    public void onClick() {
                        try {
                            getTransportService().removeCars(Arrays.asList(getModelObject()));
                        }
                        catch (Throwable e) {
                            CarsPage.this.error(e.getMessage());
                        }
                    }
                });
            }
        };

        add(dataView);
    }

    private IResource createImageResource(byte[] imageBytes) {
        BufferedImage bufferedImage;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
            bufferedImage = ImageIO.read(inputStream);
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }

        BufferedDynamicImageResource imageResource = new BufferedDynamicImageResource();
        imageResource.setImage(bufferedImage);

        return imageResource;
    }

    private class CarsDataProvider extends ListDataProvider<Car> {

        private IModel<List<Car>> carsModel;

        public CarsDataProvider(IModel<List<Car>> carsModel) {
            this.carsModel = carsModel;
        }

        @Override
        protected List<Car> getData() {
            return carsModel.getObject();
        }
    }

    private class CarsModel extends LoadableDetachableModel<List<Car>> {

        @Override
        protected List<Car> load() {
            return getTransportService().getCars();
        }
    }
}