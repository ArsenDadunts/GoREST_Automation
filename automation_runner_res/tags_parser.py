
def tags_parser(args):

    excluded_params = parse_exclude_params(args)
    tags = ' and '.join([str(f"@{elem}") for elem in args.tags])
    value = f"{tags} {excluded_params}".strip()
    return value

def parse_exclude_params(args):
    excluded_opt = 'and not ignore ' if args.tags else 'not ignore '
    sep = 'and not' if args.tags else 'not'
    if args.exclude:
        for tag in args.exclude:
            excluded_opt += f'{sep} @{tag} '
            if sep in {'and not', 'not'}:
                sep = 'and not'
    return excluded_opt
