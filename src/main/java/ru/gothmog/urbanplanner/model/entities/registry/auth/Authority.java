package ru.gothmog.urbanplanner.model.entities.registry.auth;

/**
 * Роли безопасности приложения. Просмотр - пока не определен, Редактирование -
 * пока не определен, Администрирование - доступ ко всему Заблокирован - то же
 * самое, что и анонимный вход, только есть возможность входа
 *
 * @author d.grushetskiy
 */
public enum Authority {

    ROLE_VIEW("Просмотр"),

    ROLE_EDIT("Редактирование"),

    ROLE_ADMIN(
            "Администратор (ввод учетных записей, распределение прав доступа, просмотр данных подсистемы истории изменения информации)"),

    ROLE_REGISTRY_EDITOR(
            "Добавление/редактирование/удаление значений в справочники"),

    ROLE_REQUEST_VIEWER("Просмотр заявок"),

    ROLE_REQUEST_EXECUTOR("Исполнитель заявок"),

    ROLE_REQUEST_SUPERUSER("Администратор заявок (канцелярия)"),

    ROLE_REQUEST_REMOVER("Удаление заявок"),

    ROLE_REPORTS("Формирование отчетов"),

    ROLE_IMPORTER("Загрузка информации из ИС МФЦ и импорт данных в ИС 'Дело'"),

    ROLE_BLOCKED("Заблокирован");

    private String nameAuthority;

    Authority(String nameAuthority) {
        this.nameAuthority = nameAuthority;
    }

    public String getNameAuthority() {
        return nameAuthority;
    }

    @Override
    public String toString() {
        return nameAuthority;
    }
}
