package seedu.pivot.logic.commands.documentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ALL_CASES;

import java.util.List;

import seedu.pivot.commons.core.Messages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Document;

public class DeleteDocumentCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_DOCUMENT_SUCCESS = "Deleted document: %1$s";

    private final Index caseIndex;
    private final Index documentIndex;

    /**
     * Creates a DeleteDocumentCommand to delete a {@code Document} at index @code documentIndex}
     * from the case at index {@code caseIndex}.
     *
     * @param caseIndex index of a case in the list.
     * @param documentIndex document index in the document list.
     */
    public DeleteDocumentCommand(Index caseIndex, Index documentIndex) {
        this.caseIndex = caseIndex;
        this.documentIndex = documentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Case> lastShownList = model.getFilteredCaseList();

        assert(StateManager.atCasePage()) : "Program should be at case page";
        assert(caseIndex.getZeroBased() >= lastShownList.size()) : "index should be valid";

        //get case from state
        Case stateCase = lastShownList.get(caseIndex.getZeroBased());
        List<Document> updatedDocuments = stateCase.getDocuments();

        //document index validation in model
        if (documentIndex.getZeroBased() >= updatedDocuments.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCUMENT_DISPLAYED_INDEX);
        }

        //remove document
        Document documentToDelete = updatedDocuments.get(documentIndex.getZeroBased());
        updatedDocuments.remove(documentToDelete);

        Case updatedCase = new Case(stateCase.getTitle(), stateCase.getDescription(),
                stateCase.getStatus(), updatedDocuments, stateCase.getSuspects(),
                stateCase.getVictims(), stateCase.getWitnesses(), stateCase.getTags());

        //update model
        model.setCase(stateCase, updatedCase);
        model.updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);

        return new CommandResult(String.format(MESSAGE_DELETE_DOCUMENT_SUCCESS, documentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDocumentCommand // instanceof handles nulls
                && caseIndex.equals(((DeleteDocumentCommand) other).caseIndex)
                && documentIndex.equals(((DeleteDocumentCommand) other).documentIndex)); // state check
    }

}