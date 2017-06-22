import java.util.ListResourceBundle;

/**
 * Created by danil on 22.06.2017.
 */
public class Locale_en extends ListResourceBundle{
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"Остатки еды", "Food residius"},
                {"Имя", "Name"},
                {"Вес", "Weght"},
                {"Дата создания", "Date of creation"},
                {"Безыменный", "Nameless"},
                {"Фильтровать", "Filter"},
                {"Информация", "Information"},
                {"Очистить", "Clear"},
                {"Удалить элементы", "Remove elements"},
                {"Выбрать файлы", "Choose files"},
                {"Сохранить", "Save"},
                {"Фильтр имени", "Name filter"},
                {"Фильтр веса", "Weight filter"},
                {"Фильтр даты", "Date filter"},
                {"Хорошо", "Ok"},
                {"Отмена", "Cancel"},
                {"Цвет текста кнопок:", "Color of button's text:"},
                {"Редактирование таблицы:", "Editable table:"},
                {"Да", "Yes"},
                {"Нет", "No"},
                {"Размер шрифта кнопок:", "Buttons font size:"},
                {"Сохранено.", "Saved."},
                {"Отменить", "Cancel"},
                {"По умолчанию", "Default"},
                {"Тип коллекции: ", "Collection type: "},
                {"\nКоличество элементов: ", "\nElements count: "},
                {"Вы точно хотите удалить все элементы коллекции?", "Do you realy want to delete all elements?"},
                {"Введите строку в формате json", "Enter the string in json format"},
                {"Выберете файл или сохраните по умолчанию?", "Choose file or save by default?"},
                {"Выбрать", "Choose"},
                {"Элемент уже существует", "Element already exists"},
                {"Добавить", "Add"},
                {"Удалить", "Remove"},
                {"Нет совпадений", "No matches"},
                {"1 из ", "1 from "},
                {" из ", " from "},
                {"Неверный формат фильтра", "Incorrect filter format"},
                {"Не удалось записать данные в xml-file", "Couldn't write to xml-file"},
                {"Что-то не так с данными в файле. Проверьте их корректность.", "Something wrong with the data. Check correctness."},
                {"Файл не найден. Проверьте корректность пути.", "Couldn't find the file. Check the path."},
                {"Произошла неизвестная ошибка.", "An unknown error occurred."},
        };
    }
}
